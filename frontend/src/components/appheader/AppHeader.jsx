import {AppBar, Typography} from "@mui/material";
import Logo from "../../assets/dodo_logo_contained.svg";
import {Box} from "@mui/system";

const AppHeader = () => {
    return (
        <AppBar position="relative">
            <Box padding={1} display={"flex"}
                 alignItems="end"
                 justifyContent={"center"}
            >
                <Typography variant="h6"
                            component="div"
                            style={{ "-webkit-text-stroke": "1px black", color: "FEF2D6" }}
                            sx={{color: "#FEF2D6"}}
                >
                    Warming up the
                </Typography>
                <img src={Logo} alt="Logo" height={"50px"} color={"white"} style={{marginBottom: "4px"}}/>
            </Box>
        </AppBar>
    )
}

export default AppHeader;