import {useGetGeneralMenuQuery} from "../../api/api.js";
import {useState} from "react";
import {
    Button,
    Divider,
    Grid,
    Paper,
    TextField,
} from "@mui/material";
import {Box} from "@mui/system";
import TransferListForChoose from "./TransferListForChoose.jsx";
import TransferListChosen from "./TransferListChosen.jsx";


const TransferList = () => {
    const {data} = useGetGeneralMenuQuery();

    const [selected, setSelected] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    if (!data) return null;

    // Фильтруем пиццы по поисковому запросу
    const filteredData = data.filter(item =>
        item.title.toLowerCase().includes(searchTerm.toLowerCase())
    );

    // Группируем данные по категориям
    const categories = [...new Set(filteredData.map(pizza => pizza.category))];

    const handleToggle = (item) => {
        const currentIndex = selected.findIndex(i => i.id === item.id);
        const newSelected = [...selected];

        if (currentIndex === -1) {
            newSelected.push({
                ...item,
                count: 1,
            });
        } else {
            newSelected.splice(currentIndex, 1);
        }
        setSelected(newSelected);
    };

    const handleDelete = (id) => {
        setSelected((state) => {
            return state.filter(item => item.id !== id)
        });
    }

    const handleIncrement = (id) => {
        const arrayIndex = selected.findIndex(i => i.id === id);
        const oldCount = selected[arrayIndex].count

        setSelected((prevState) => {
            return prevState.map(item => (
                item.id === id ? { ...item, count: oldCount + 1 } : item
            ))
        });
    }
    const handleDecrement = (id) => {
        const arrayIndex = selected.findIndex(i => i.id === id);
        const oldCount = selected[arrayIndex].count

        if (oldCount === 1) {
            handleDelete(id)
        } else {
            setSelected((prevState) => {
                return prevState.map(item => (
                    item.id === id ? { ...item, count: oldCount - 1 } : item
                ))
            });
        }

    }

    return (
        <Grid container spacing={2} mt={2}>
            <Grid item xs={12} md={6}>
                <Paper>
                    <Box sx={{ padding: 2 }}>
                        <TextField
                            label="Поиск"
                            variant="outlined"
                            fullWidth
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                            sx={{ marginBottom: 2 }}
                        />

                        <TransferListForChoose
                            categories={categories}
                            filteredData={filteredData}
                            selected={selected}
                            handleToggle={handleToggle}
                        />
                    </Box>
                </Paper>
            </Grid>
            <Grid item xs={12} md={6}>
                <Paper>
                    <TransferListChosen
                        selected={selected}
                        handleDecrement={handleDecrement}
                        handleIncrement={handleIncrement}
                        handleDelete={handleDelete}
                    />
                </Paper>
            </Grid>
            <Grid item xs={12}>
                <Button variant={"contained"}
                        fullWidth={true}
                >
                    Отправить
                </Button>
            </Grid>
        </Grid>
    );

}

export default TransferList