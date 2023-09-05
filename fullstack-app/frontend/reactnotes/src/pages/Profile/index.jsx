import {Avatar, Container, Form} from "./styles.js";
import {FiArrowLeft, FiCamera, FiLock, FiMail, FiUser} from "react-icons/fi";
import {Input} from "../../components/Input/index.jsx";
import Button from "../../components/Button/index.jsx";
import {Link} from "react-router-dom";
import {useState} from "react";

export function Profile() {
  const {user} = useAuth()
  const [name, setName] = useState(user.name)
  const [email, setEmail] = useState(user.email)
  const [oldPassword, setOldPassword] = useState("")
  const [newPassword, setNewPassword] = useState("")


  return (
    <Container>
      <header>
        <Link to="/">
          <FiArrowLeft />
        </Link>
      </header>

      <Form>
        <Avatar>
          <img src="https://github.com/valdenidelgado.png" alt="Foto de perfil"/>
          <label htmlFor="avatar">
            <FiCamera />
            <input id="avatar" type="file" />
          </label>
        </Avatar>
        <Input placeholder="Nome" type="text" icon={FiUser} value={name} onChange={e => setName(e.target.value)} />
        <Input placeholder="E-mail" type="text" icon={FiMail} value={email} onChange={e => setEmail(e.target.value)} />
        <Input placeholder="Senha atual" type="password" icon={FiLock} onChange={e => setOldPassword(e.target.value)} />
        <Input placeholder="Nova senha" type="password" icon={FiLock} onChange={e => setNewPassword(e.target.value)} />
        <Button title="Salvar" />
      </Form>
    </Container>
  )
}