import Button from "../../components/Button";
import {Header} from "../../components/Header";
import {Section} from "../../components/Section";
import {Container, Content, Links} from "./styles";
import {Tag} from "../../components/Tag/index.jsx";
import {ButtonText} from "../../components/ButtonText/index.jsx";

export function Details() {
  return (
    <Container>
      <Header/>

      <main>
        <Content>
          <ButtonText title="Excluir nota"/>
          <h1>
            Introdução ao React
          </h1>
          <p>
            O Fabuloso Gerador de Lero-lero v2.0 é capaz de gerar qualquer quantidade de texto vazio e prolixo,
            ideal para engrossar uma tese de mestrado, impressionar seu chefe ou preparar discursos capazes de curar
            a insônia da platéia. Basta informar um título pomposo qualquer (nos moldes do que está sugerido aí embaixo)
            e a quantidade de frases desejada. Voilá! Em dois nano-segundos você terá um texto - ou mesmo um livro
            inteiro - pronto para impressão. Ou, se preferir, faça copy/paste para um editor de texto para formatá-lo
            mais sofisticadamente. Lembre-se: aparência é tudo, conteúdo é nada.
          </p>

          <Section title="Links úteis">
            <Links>
              <li><a href="#">https://www.github.com</a></li>
              <li><a href="#">https://www.github.com</a></li>
            </Links>
          </Section>
          <Section title="Marcadores">
            <Tag title="express"/>
            <Tag title="nodejs"/>
          </Section>
          <Button title="Voltar"/>
        </Content>
      </main>
    </Container>
  );
}