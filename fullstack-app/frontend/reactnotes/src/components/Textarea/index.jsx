import {Container} from "./styles.js";

export function Textarea( {value, ...rest} ) {
  return (
    <Container {...rest}>
      {value}
    </Container>
  )
}