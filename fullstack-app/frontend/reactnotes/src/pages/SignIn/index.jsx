import {Background, Container, Form} from "./styles.js";
import {Input} from "../../components/Input/index.jsx";
import {FiMail, FiLock} from "react-icons/fi";
import Button from "../../components/Button/index.jsx";
import {Link} from "react-router-dom";
import {useAuth} from "../../hooks/auth.jsx";
import {useState} from "react";

export function SignIn() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const { signIn } = useAuth()

  function handleSignIn() {
    signIn({email, password})
  }

  return (
    <Container>
      <Form>
        <h1>Rocket Notes</h1>
        <p>Aplicação para salvar e gerenciar seus links úteis.</p>

        <h2>Faça seu login.</h2>
        <Input type="text" placeholder="E-mail" icon={FiMail} onChange={e => setEmail(e.target.value)} />
        <Input type="password" placeholder="Senha" icon={FiLock} onChange={e => setPassword(e.target.value)} />
        <Button title="Entrar" onClick={handleSignIn} />

        <Link to="/register">
          Criar conta
        </Link>
      </Form>
      <Background/>
    </Container>
  )
}