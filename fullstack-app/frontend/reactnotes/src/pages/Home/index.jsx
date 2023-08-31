import {FiPlus, FiSearch} from "react-icons/fi";
import {Container, Brand, Menu, Search, Content, NewNote} from './styles';
import {Header} from "../../components/Header/index.jsx";
import {ButtonText} from "../../components/ButtonText/index.jsx";
import {Input} from "../../components/Input/index.jsx";
import {Section} from "../../components/Section/index.jsx";
import {Note} from "../../components/Note/index.jsx";

export function Home() {
  return (
    <Container>
      <Brand>
        <h1>Rocketnotes</h1>
      </Brand>

      <Header/>

      <Menu>
        <li><ButtonText title="Todos" isActive/></li>
        <li><ButtonText title="React"/></li>
        <li><ButtonText title="Nodejs"/></li>
      </Menu>
      <Search>
        <Input placeholder="Pesquisar pelo título." icon={FiSearch} />
      </Search>
      <Content>
        <Section title="Minhas Notas">
          <Note data={{
            title: 'React',
            tags: [
              {id: 1, name: 'React'},
              {id: 2, name: 'Node'},
            ]
          }} />
        </Section>
      </Content>
      <NewNote>
        <FiPlus />
        Criar nota
      </NewNote>
    </Container>
  )
}