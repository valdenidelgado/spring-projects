import {RiShutDownLine} from "react-icons/ri";
import {Container, Profile, Logout} from "./styles";
import {useAuth} from "../../hooks/auth.jsx";

export function Header() {
  const { signOut } = useAuth()

  return (
    <Container>
      <Profile to="/profile">
        <img src="https://github.com/valdenidelgado.png" alt="Foto do usuário"/>
        <div>
          <span>Bem-vindo,</span>
          <strong>Valdeni Delgado</strong>
        </div>
      </Profile>

      <Logout onClick={signOut}>
        <RiShutDownLine/>
      </Logout>
    </Container>
  )
}