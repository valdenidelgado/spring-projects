import {Container, Form} from "./styles.js";
import {Header} from "../../components/Header/index.jsx";
import {Input} from "../../components/Input/index.jsx";
import {Textarea} from "../../components/Textarea/index.jsx";
import {Section} from "../../components/Section/index.jsx";
import {NoteItem} from "../../components/NoteItem/index.jsx";
import Button from "../../components/Button/index.jsx";
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {api} from "../../services/api.js";
import {ButtonText} from "../../components/ButtonText/index.jsx";

export function New() {
  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')

  const [links, setLinks] = useState([])
  const [newLink, setNewLink] = useState('')

  const [tags, setTags] = useState([])
  const [newTag, setNewTag] = useState('')

  const navigate = useNavigate()

  function handleBack() {
    navigate(-1)
  }

  function handleAddLink() {
    setLinks(prev => [...prev, newLink])
    setNewLink('')
  }

  function handleRemoveLink(deleted) {
    setLinks(prev => prev.filter(link => link !== deleted))
  }

  function handleAddTag() {
    setTags(prev => [...prev, newTag])
    setNewTag('')
  }

  function handleRemoveTag(deleted) {
    setTags(prev => prev.filter(tag => tag !== deleted))
  }

  async function handleNewNote() {
    if (!title) {
      return alert('Você precisa adicionar um título para salvar a nota!')
    }

    if (newTag) {
      return alert('Você precisa adicionar a nova tag antes de salvar a nota!')
    }
    if (newLink) {
      return alert('Você precisa adicionar o novo link antes de salvar a nota!')
    }

    await api.post('/notes', {
      title,
      description,
      tags,
      links,
    })

    alert('Nota criada com sucesso!')
    navigate(-1)
  }

  return (
    <Container>
      <Header/>
      <main>
        <Form>
          <header>
            <h1>Criar nota</h1>
            <ButtonText title="Voltar" onClick={handleBack} />
          </header>

          <Input placeholder="Título" onChange={e => setTitle(e.target.value)}/>
          <Textarea placeholder="Observações " onChange={e => setDescription(e.target.value)}/>

          <Section title="Links úteis">
            {
              links.map((link, index) => (
                <NoteItem key={index} value={link} onClick={() => handleRemoveLink(link)}/>
              ))
            }

            <NoteItem isNew placeholder="Novo link" value={newLink} onChange={e => setNewLink(e.target.value)}
                      onClick={handleAddLink}/>
          </Section>

          <Section title="Marcadores">
            <div className="tags">
              {
                tags.map((tag, index) => (
                  <NoteItem key={String(index)} value={tag} onClick={() => handleRemoveTag(tag)}/>
                ))
              }

              <NoteItem
                isNew
                placeholder="Nova tag"
                value={newTag}
                onChange={e => setNewTag(e.target.value)}
                onClick={handleAddTag}
              />
            </div>
          </Section>

          <Button title="Salvar" onClick={handleNewNote}/>
        </Form>
      </main>
    </Container>
  )
}