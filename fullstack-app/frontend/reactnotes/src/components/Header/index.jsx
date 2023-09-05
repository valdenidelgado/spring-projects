import {RiShutDownLine} from "react-icons/ri";
import {Container, Profile, Logout} from "./styles";
import {useAuth} from "../../hooks/auth.jsx";
import {api} from "../../services/api.js";
import avatarPlaceHolder from "../../assets/Blank-Avatar.png";

export function Header() {
  const { signOut, user } = useAuth()

  const avatarUrl = user.avatar ? `${api.defaults.baseURL}/files/${user.avatar}` : avatarPlaceHolder

  return (
    <Container>
      <Profile to="/profile">
        <img src={avatarUrl} alt="Foto do usuário"/>
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