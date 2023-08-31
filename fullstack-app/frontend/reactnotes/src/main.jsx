import React from 'react'
import ReactDOM from 'react-dom/client'
import {ThemeProvider} from 'styled-components'
import GlobalStyles from './styles/global'
import theme from './styles/theme.js'
import {New} from "./pages/New/index.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyles/>
      <New/>
    </ThemeProvider>
  </React.StrictMode>,
)
