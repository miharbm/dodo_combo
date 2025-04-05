import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from "./components/app/App.jsx";
import {Provider} from "react-redux";
import {store} from "./store/index.js";
import {CssBaseline} from "@mui/material";
import {ThemeProvider} from "@mui/system";
import theme from "./styles/theme.js";


createRoot(document.getElementById('root')).render(
  <StrictMode>
      <Provider store={store}>
          <ThemeProvider theme={theme}>
              <CssBaseline />
              <App />
          </ThemeProvider>
      </Provider>
  </StrictMode>,
)
