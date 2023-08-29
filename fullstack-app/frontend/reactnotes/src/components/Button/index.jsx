import { Container } from "./styles";

// eslint-disable-next-line react/prop-types
export default function Button({ title, loading = false, ...rest }) {
  return (
    <Container 
      type="button"
      disabled={loading}
      {...rest}
      >
      { loading ? 'Carregando...' : title }
    </Container>
  )
}