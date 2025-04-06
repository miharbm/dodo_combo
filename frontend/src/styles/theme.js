import { createTheme } from "@mui/material/styles";

const theme = createTheme({
    palette: {
        mode: 'light', // Установите режим светлой темы
        primary: {
            main: "rgb(255, 105, 0)",
            contrastText: '#ffffff',
        },
        secondary: {
            main: '#f54242', // Вторичный цвет
        },
        text: {
            primary: "#393939"
        },
        background: {
            default: "#e8e8e8",  // Цвет основного фона
            paper: "#ffffff",     // Цвет фона для `Paper`
        },
    },
    typography: {
        fontFamily: `'vagroundcyrillic', 'Roboto', 'Helvetica', 'Arial', sans-serif`,
        h1: {
            fontFamily: `'vagroundcyrillic', 'Roboto', 'Helvetica', 'Arial', sans-serif`,
        },
    }
});

export default theme