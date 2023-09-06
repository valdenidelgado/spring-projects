import {FiPlus, FiSearch} from "react-icons/fi";
import {Container, Brand, Menu, Search, Content, NewNote} from './styles';
import {Header} from "../../components/Header/index.jsx";
import {ButtonText} from "../../components/ButtonText/index.jsx";
import {Input} from "../../components/Input/index.jsx";
import {Section} from "../../components/Section/index.jsx";
import {Note} from "../../components/Note/index.jsx";
import {useEffect, useState} from "react";
import {api} from "../../services/api";

export function Home() {
  const [tags, setTags] = useState([])
  const [tagSelected, setTagSelected] = useState([])

  function handleTagSelected(tagName) {
    const alreadySelected = tagSelected.includes(tagName)

    if (alreadySelected) {
      const filteredTags = tagSelected.filter(tag => tag !== tagName)
      setTagSelected(filteredTags)
    } else {
      setTagSelected(prevState => [...prevState, tagName])
    }
  }

  useEffect(() => {
    // add groupby no backend para não ter tag duplicada
    async function fetchTags() {
      const response = await api.get('/tags');
      setTags(response.data);
    }

    fetchTags();
  }, []);

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
        <Input placeholder="Pesquisar pelo título." icon={FiSearch}/>
      </Search>
      <Content>
        <Section title="Minhas Notas">
          <Note data={{
            title: 'React',
            tags: [
              {id: 1, name: 'React'},
              {id: 2, name: 'Node'},
            ]
          }}/>
        </Section>
      </Content>
      <NewNote to="/new">
        <FiPlus/>
        Criar nota
      </NewNote>
    </Container>
  )
}