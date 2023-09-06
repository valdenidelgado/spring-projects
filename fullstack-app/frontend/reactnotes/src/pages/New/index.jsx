import {Container, Form} from "./styles.js";
import {Header} from "../../components/Header/index.jsx";
import {Input} from "../../components/Input/index.jsx";
import {Textarea} from "../../components/Textarea/index.jsx";
import {Section} from "../../components/Section/index.jsx";
import {NoteItem} from "../../components/NoteItem/index.jsx";
import Button from "../../components/Button/index.jsx";
import {Link} from "react-router-dom";
import {useState} from "react";

export function New() {
  const [links, setLinks] = useState([])
  const [newLink, setNewLink] = useState('')

  const [tags, setTags] = useState([])
  const [newTag, setNewTag] = useState('')

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

  return (
    <Container>
      <Header/>
      <main>
        <Form>
          <header>
            <h1>Criar nota</h1>
            <Link to="/">voltar</Link>
          </header>

          <Input placeholder="Título"/>
          <Textarea placeholder="Observações "/>

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
                  <NoteItem key={index} value={tag} onClick={() => handleRemoveTag(tag)}/>
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

          <Button title="Salvar"/>
        </Form>
      </main>
    </Container>
  )
}