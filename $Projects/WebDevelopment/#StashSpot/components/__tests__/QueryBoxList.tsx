import React, { useEffect, useState } from "react";
import { View, Text, FlatList } from "react-native";
import { supabase } from "./supabase";

export default function BoxItemList() {
    const [todos, setTodos] = useState([]);

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        const { data, error } = await supabase.from("lk_items").select("*");

        if (error) {
            console.error("Error fetching box items:", error);
        } else {
            setTodos(data);
        }
    };

    return (
        <View>
            <FlatList
                data={todos}
                keyExtractor={(item) => item.id.toString()}
                renderItem={({ item }) => <Text>{item.title}</Text>}
            />
        </View>
    );
}
