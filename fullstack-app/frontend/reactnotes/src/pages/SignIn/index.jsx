import {Background, Container, Form} from "./styles.js";
import {Input} from "../../components/Input/index.jsx";
import {FiMail, FiLock} from "react-icons/fi";
import Button from "../../components/Button/index.jsx";
import {Link} from "react-router-dom";

export function SignIn() {
  return (
    <Container>
      <Form>
        <h1>Rocket Notes</h1>
        <p>Aplicação para salvar e gerenciar seus links úteis.</p>

        <h2>Faça seu login.</h2>
        <Input type="text" placeholder="E-mail" icon={FiMail}/>
        <Input type="password" placeholder="Senha" icon={FiLock}/>
        <Button title="Entrar" />

        <Link to="/register">
          Criar conta
        </Link>
      </Form>
      <Background />
    </Container>
  )
}