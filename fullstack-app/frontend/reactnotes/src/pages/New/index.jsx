import {Container, Form} from "./styles.js";
import {Header} from "../../components/Header/index.jsx";
import {Input} from "../../components/Input/index.jsx";
import {Textarea} from "../../components/Textarea/index.jsx";
import {Section} from "../../components/Section/index.jsx";
import {NoteItem} from "../../components/NoteItem/index.jsx";
import Button from "../../components/Button/index.jsx";
import {Link} from "react-router-dom";

export function New() {
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
            <NoteItem value="https://google.com/"/>
            <NoteItem isNew placeholder="Novo link"/>
          </Section>

          <Section title="Marcadores">
            <div className="tags">
              <NoteItem value="react"/>
              <NoteItem isNew placeholder="Nova tag"/>
            </div>
          </Section>

          <Button title="Salvar"/>
        </Form>
      </main>
    </Container>
  )
}