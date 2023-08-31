import {Container} from "./styles.js";

export function Tag({title, ...rest}) {
  return (
    <Container {...rest}>
      {title}
    </Container>
  )
}