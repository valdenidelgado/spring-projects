import {Avatar, Container, Form} from "./styles.js";
import {FiArrowLeft, FiCamera, FiLock, FiMail, FiUser} from "react-icons/fi";
import {Input} from "../../components/Input/index.jsx";
import Button from "../../components/Button/index.jsx";

export function Profile() {
  return (
    <Container>
      <header>
        <a href="/">
          <FiArrowLeft />
        </a>
      </header>

      <Form>
        <Avatar>
          <img src="https://github.com/valdenidelgado.png" alt="Foto de perfil"/>
          <label htmlFor="avatar">
            <FiCamera />
            <input id="avatar" type="file" />
          </label>
        </Avatar>
        <Input placeholder="Nome" type="text" icon={FiUser} />
        <Input placeholder="E-mail" type="text" icon={FiMail} />
        <Input placeholder="Senha atual" type="password" icon={FiLock} />
        <Input placeholder="Nova senha" type="password" icon={FiLock} />
        <Button title="Salvar" />
      </Form>
    </Container>
  )
}