import { createTheme } from "@mui/material/styles";

const theme = createTheme({
    palette: {
        mode: 'light', // Установите режим светлой темы
        primary: {
            main: '#3d7df5', // Основной цвет
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
        h1: {
            fontFamily: '"Oswald", sans-serif',
        },
    }
});

export default theme