import {createContext, useContext, useEffect, useState} from "react";
import {api} from "../services/api";

const AuthContext = createContext({});

// eslint-disable-next-line react/prop-types
function AuthProvider({children}) {

  const [data, setData] = useState({})

  async function signIn({email, password}) {
    try {
      const response = await api.post('/sessions', {email, password})
      const {user, token} = response.data

      localStorage.setItem('@rocketnotes:user', JSON.stringify(user))
      localStorage.setItem('@rocketnotes:token', token)

      api.defaults.headers.common['Authorization'] = `Bearer ${token}`

      setData({user, token})

    } catch (e) {
      if (e.response) {
        alert(e.response.data.message)
      } else {
        alert('Erro ao realizar login')
      }
    }
  }

  function signOut() {
    localStorage.removeItem('@rocketnotes:user')
    localStorage.removeItem('@rocketnotes:token')

    setData({})
  }

  async function updateProfile({user, avatarFile}) {
    try {

      if (avatarFile) {
        const data = new FormData()
        data.append('avatar', avatarFile)
        const response = await api.patch('/users/avatar', data)
        user.avatar = response.data.avatar
      }

      await api.put('/users', user)
      localStorage.setItem('@rocketnotes:user', JSON.stringify(user))

      setData({user, token: data.token})

      alert('Perfil atualizado com sucesso')
    } catch (e) {
      if (e.response) {
        alert(e.response.data.message)
      } else {
        alert('Erro ao atualizar perfil')
      }
    }
  }

  useEffect(() => {
    const token = localStorage.getItem('@rocketnotes:token')
    const user = localStorage.getItem('@rocketnotes:user')

    if (token && user) {
      api.defaults.headers.common['Authorization'] = `Bearer ${token}`
      setData({token, user: JSON.parse(user)})
    }
  }, []);

  return (
    <AuthContext.Provider value={{signIn, signOut, updateProfile, user: data.user }}>
      {children}
    </AuthContext.Provider>
  )
}

function useAuth() {
  return useContext(AuthContext);
}

export {AuthProvider, useAuth};