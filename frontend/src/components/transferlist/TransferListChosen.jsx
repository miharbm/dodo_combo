import {Grid, IconButton, List, ListItem, ListItemText, Typography} from "@mui/material";
import {Box} from "@mui/system";
import DeleteIcon from "@mui/icons-material/Delete";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";

const TransferListChosen = (props) => {
    const {selected, handleDecrement, handleIncrement, handleDelete} = props;

    return (
        <Box padding={2}>
            <Typography variant="h5" sx={{ mx: 1, display: "flex", alignItems: "center" }} height={"56px"} >
                Выбранные товары
            </Typography>
            <Box sx={{ maxHeight: 400, overflow: "auto", mt: 2 }}>
                <List>
                    {selected.map((item) => (
                        <ListItem key={item.id}
                                  secondaryAction={
                                      <SecondaryAction
                                          item={item}
                                          handleDecrement={handleDecrement}
                                          handleIncrement={handleIncrement}
                                          handleDelete={handleDelete}
                                      />
                                  }
                        >
                            <ListItemText
                                primary={item.title}
                                secondary={`Цена: ${item.price} руб.`}
                            />
                        </ListItem>
                    ))}
                </List>
            </Box>
        </Box>
    )
}

const SecondaryAction = (props) => {
    const {item, handleDecrement, handleIncrement, handleDelete} = props;

    return (
        <Grid container>
            <Grid item>
                <IconButton onClick={() => handleDecrement(item.id)}
                            disabled={item.count <= 1}
                >
                    <RemoveIcon />
                </IconButton>
            </Grid>
            <Grid item display="flex" justifyContent="center" alignItems="center">
                <Typography variant="body1" sx={{ mx: 1 }}>{item.count}</Typography>
            </Grid>
            <Grid item>
                <IconButton onClick={() => handleIncrement(item.id)}>
                    <AddIcon />
                </IconButton>
            </Grid>
            <Grid item>
                <IconButton edge="end" onClick={() => handleDelete(item.id)}>
                    <DeleteIcon />
                </IconButton>
            </Grid>
        </Grid>
    )
}

export default TransferListChosen