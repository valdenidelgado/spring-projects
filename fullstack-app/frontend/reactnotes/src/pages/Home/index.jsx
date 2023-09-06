import {FiPlus, FiSearch} from "react-icons/fi";
import {Container, Brand, Menu, Search, Content, NewNote} from './styles';
import {Header} from "../../components/Header/index.jsx";
import {ButtonText} from "../../components/ButtonText/index.jsx";
import {Input} from "../../components/Input/index.jsx";
import {Section} from "../../components/Section/index.jsx";
import {Note} from "../../components/Note/index.jsx";
import {useEffect, useState} from "react";
import {api} from "../../services/api";
import {useNavigate} from "react-router-dom";

export function Home() {
  const [tags, setTags] = useState([])
  const [tagSelected, setTagSelected] = useState([])
  const [search, setSearch] = useState('')
  const [notes, setNotes] = useState([])

  const navigate = useNavigate()

  function handleTagSelected(tagName) {

    if (tagName === "all") {
      setTagSelected([])
      return
    }

    const alreadySelected = tagSelected.includes(tagName)

    if (alreadySelected) {
      const filteredTags = tagSelected.filter(tag => tag !== tagName)
      setTagSelected(filteredTags)
    } else {
      setTagSelected(prevState => [...prevState, tagName])
    }
  }

  function handleDetails(id) {
    navigate(`/details/${id}`)
  }

  useEffect(() => {
    // add groupby no backend para não ter tag duplicada
    async function fetchTags() {
      const response = await api.get('/tags');
      setTags(response.data);
    }

    fetchTags();
  }, []);

  useEffect(() => {
    async function fetchNotes() {
      const response = await api.get(`/notes?title=${search}&tags=${tagSelected}`);
      setNotes(response.data);
    }

    fetchNotes();
  }, [tagSelected, search]);

  return (
    <Container>
      <Brand>
        <h1>Rocketnotes</h1>
      </Brand>

      <Header/>

      <Menu>
        <li><ButtonText title="Todos" onClick={() => handleTagSelected("all")} isActive={tagSelected.length === 0}/>
        </li>
        {
          tags && tags.map(tag => (
            <li key={String(tag.id)}><ButtonText title={tag.name} onClick={() => handleTagSelected(tag.name)}
                                                 isActive={tagSelected.includes(tag.name)}/></li>
          ))
        }
      </Menu>
      <Search>
        <Input placeholder="Pesquisar pelo título." icon={FiSearch} onChange={e => setSearch(e.target.value)}/>
      </Search>
      <Content>
        <Section title="Minhas Notas">
          {
            notes.map(note => (
              <Note key={String(note.id)} data={note} onClick={() => handleDetails(note.id)} />
            ))
          }
        </Section>
      </Content>
      <NewNote to="/new">
        <FiPlus/>
        Criar nota
      </NewNote>
    </Container>
  )
}