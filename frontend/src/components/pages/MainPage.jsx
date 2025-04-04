import Container from "@mui/material/Container";

import TransferList from "../transferlist/TransferList.jsx";
import ReceivedCombosView from "../receivedcombosview/ReceivedCombosView.jsx";

const MainPage = () => {

    return (
        <Container >
            <TransferList/>
            <ReceivedCombosView/>
        </Container>
    )
}

export default MainPage