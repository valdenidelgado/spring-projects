import {Container} from "./styles.js";

export function Input({icon: Icon, ...rest}) {
  return (
    <Container>
      {Icon && <Icon size={20} />}
      <input {...rest} />
    </Container>
  )
}