import {Container} from "./styles.js";

export function ButtonText({title, ...rest}) {
  return (
    <Container
      type="button"
      {...rest}
    >
      {title}
    </Container>
  );
}