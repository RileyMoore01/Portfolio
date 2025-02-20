import React, { useState } from "react";
import { View, TextInput, Button, Alert } from "react-native";
import { supabase } from "./supabase";

export default function AddBoxItem() {
    const [title, setTitle] = useState("");

    const addTodo = async () => {
        if (title.trim() === '') {
            Alert.alert('Error', 'Please enter a todo title.');
            return;
        }
        
        const { data, error } = await supabase
            .from("lk_items")
            .insert([{ title }]);

        if (error) {
            Alert.alert("Error", error.message);
        } else {
            Alert.alert("Success", "Box item added successfully");
            setTitle("");
        }
    };

    return (
        <View>
            <TextInput
                placeholder="Box Item"
                value={title}
                onChangeText={setTitle}
            />
            <Button title="Add Item" onPress={addTodo} />
        </View>
    );
}
