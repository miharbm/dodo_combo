import {Box} from "@mui/system";
import {Button, List, ListItemButton, ListItemText, Typography} from "@mui/material";

const TransferListForChoose = (props) => {
    const {categories, filteredData, selected, handleToggle} = props;


    return (
        <Box sx={{ height: 400, overflow: "auto" }}>
            {categories?.map((category) => (
                <Box key={category} sx={{ marginBottom: 2 }}>
                    <Typography variant="h6" sx={{ mx: 1, display: "flex", alignItems: "center", position: "sticky" }} height={"56px"} >
                        {category}
                    </Typography>
                    <List>
                        {filteredData
                            .filter((item) => item.category === category)
                            .map((item) => (
                                <ListItemButton
                                    key={item.id}
                                    selected={selected.findIndex(i => i.id === item.id) > -1}
                                    onClick={() => handleToggle(item)}
                                >
                                    <ListItemText
                                        primary={item.title}
                                        secondary={`Цена: ${item.price} руб.`}
                                    />
                                </ListItemButton>
                            ))}
                    </List>
                </Box>
            ))}
        </Box>
    )
}

export default TransferListForChoose;