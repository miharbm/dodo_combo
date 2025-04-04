import {Button, Fab, Grid, IconButton, List, ListItem, ListItemText, TextField, Typography} from "@mui/material";
import {Box} from "@mui/system";
import DeleteIcon from "@mui/icons-material/Delete";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import ManageSearchOutlinedIcon from "@mui/icons-material/ManageSearchOutlined.js";
import {usePickUpComboMutation} from "../../api/api.js";
import {useState} from "react";
import {useDispatch} from "react-redux";
import {setCart} from "../../reducers/cartSlice.js";


const TransferListChosen = (props) => {
    const {selected, handleDecrement, handleIncrement, handleDelete} = props;
    const [allowedMissingSlots, setAllowedMissingSlots] = useState(null)
    const dispatch = useDispatch();

    const handleTrigger = () => {
        console.log("selected", selected)
        // pickUpTrigger({items: selected})
        dispatch(setCart({items: selected, allowedMissingSlots: allowedMissingSlots}))
    }

    const handleChangeAllowedMissingProps = (event) => {
        const value = event.target.value;

        // Проверяем, является ли введенное значение числом
        if (!isNaN(value)) {
            setAllowedMissingSlots(Number(value)); // Преобразуем строку в число и обновляем стейт
        }
    };


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
            <TextField
                label="Количество возможных свободных мест в комбо"
                type="number" // Указываем тип поля как number
                value={allowedMissingSlots} // Привязываем значение к стейту
                onChange={handleChangeAllowedMissingProps} // Обработчик изменения значения
                variant="outlined" // Вариант отображения (outlined, filled, standard)
                fullWidth // Занимает всю ширину контейнера
                sx={{ mt: 2 }}
            />
            <Button variant={"contained"}
                    fullWidth={true}
                    onClick={handleTrigger}
                    sx={{ mt: 2 }}
            >
                Подобрать
            </Button>
            <Fab
                color="primary"
                aria-label="add"
                onClick={handleTrigger}
                variant="extended"
                sx={{
                    position: 'fixed',
                    bottom: 16,
                    right: 16,
                }}
            >
                <ManageSearchOutlinedIcon sx={{ mr: 1 }}/>
                Подобрать
            </Fab>
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