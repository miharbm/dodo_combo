import { Box } from "@mui/system";
import {
    Chip,
    List,
    ListItemButton,
    ListItemText,
    Stack,
    Typography
} from "@mui/material";
import { useState } from "react";

const TransferListForChoose = (props) => {
    const { categories, filteredData, selected, handleToggle } = props;
    const [selectedCategory, setSelectedCategory] = useState(null);

    const handleCategoryClick = (category) => {
        setSelectedCategory(prev => (prev === category ? null : category));
    };

    const visibleCategories = selectedCategory ? [selectedCategory] : categories;

    return (
        <Box>
            {categories?.length > 1 && (
                <Stack direction="row" spacing={1} useFlexGap flexWrap="wrap" mb={2}>
                    {categories.map((category) => (
                        <Chip
                            key={category}
                            label={category}
                            clickable
                            color={selectedCategory === category ? "primary" : "default"}
                            onClick={() => handleCategoryClick(category)}
                            size={"small"}
                        />
                    ))}
                </Stack>
            )}

            <Box sx={{ height: 400, overflow: "auto" }}>
                {visibleCategories.map((category) => (
                    <Box key={category} sx={{ marginBottom: 2 }}>
                        <Typography
                            variant="h6"
                            sx={{
                                mx: 1,
                                display: "flex",
                                alignItems: "center",
                                position: "sticky",
                            }}
                            height={"56px"}
                        >
                            {category}
                        </Typography>
                        <List>
                            {filteredData
                                .filter((item) => item.category === category)
                                .map((item) => (
                                    <ListItemButton
                                        key={item.id}
                                        selected={selected.some((i) => i.id === item.id)}
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
        </Box>
    );
};

export default TransferListForChoose;
