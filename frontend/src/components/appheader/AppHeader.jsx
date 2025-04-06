import {AppBar} from "@mui/material";
import Logo from "../../assets/dodo_logo_contained.svg";
import LogoTitle from "../../assets/logo_titile.svg";
import {Box} from "@mui/system";

const AppHeader = () => {
    return (
        <AppBar position="relative">
            <Box padding={1} display={"flex"} alignItems="end" justifyContent={"center"}>
                <img src={LogoTitle} alt="Logo" height={"25px"} color={"white"}/>
                <img src={Logo} alt="Logo" height={"50px"} color={"white"}/>
            </Box>
        </AppBar>
    )
}

export default AppHeader;