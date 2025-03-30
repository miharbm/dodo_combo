import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from "./components/app/App.jsx";
import {Provider} from "react-redux";
import {store} from "./store/index.js";
import {CssBaseline} from "@mui/material";


createRoot(document.getElementById('root')).render(
  <StrictMode>
      <Provider store={store}>
          <CssBaseline />
          <App />
      </Provider>
  </StrictMode>,
)
