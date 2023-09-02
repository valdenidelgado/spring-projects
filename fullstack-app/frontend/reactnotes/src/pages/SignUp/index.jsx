import {Background, Container, Form} from "./styles.js";
import {Input} from "../../components/Input/index.jsx";
import {FiMail, FiLock, FiUser} from "react-icons/fi";
import Button from "../../components/Button/index.jsx";
import {api} from "../../services/api.js";
import {Link, useNavigate} from "react-router-dom";
import {useState} from "react";

export function SignUp() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  function handleSignUp() {
    if (name === "" || email === "" || password === "") {
      return alert("Preencha todos os campos!");
    }

    const navigate = useNavigate()

    api.post("/users", {
      name,
      email,
      password
    }).then(() => {
      alert("Usuário cadastrado com sucesso!");
      navigate("/");
    }).catch((error) => {
      if (error.response) {
        alert(error.response.data.message)
      } else {
        alert("Erro ao cadastrar usuário!");
      }
    })
  }

  return (
    <Container>
      <Background/>
      <Form>
        <h1>Rocket Notes</h1>
        <p>Aplicação para salvar e gerenciar seus links úteis.</p>

        <h2>Crie sua conta.</h2>
        <Input type="text" placeholder="Nome" icon={FiUser} onChange={e => setName(e.target.value)}/>
        <Input type="text" placeholder="E-mail" icon={FiMail} onChange={e => setEmail(e.target.value)}/>
        <Input type="password" placeholder="Senha" icon={FiLock} onChange={e => setPassword(e.target.value)}/>
        <Button title="Cadastrar" onClick={handleSignUp}/>

        <Link to="/">
          Voltar para o login
        </Link>
      </Form>
    </Container>
  )
}