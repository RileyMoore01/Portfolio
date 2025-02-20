import React, { useState } from "react";
import { supabase } from "@/lib/supabase";
import { Input, Button } from "@rneui/themed";
import { useLocalSearchParams } from "expo-router";
import { MaterialIcons } from "@expo/vector-icons";
import { globalStyles } from "@/constants/globalStyles";
import { useNavigation } from "@react-navigation/native";
import { View, StyleSheet, Alert, TouchableOpacity } from "react-native";

export default function AddItem() {
    const navigation = useNavigation(); // navigation object
    const { id: boxId } = useLocalSearchParams(); // param from the URL

    // user input for item name and description
    const [input1, setInput1] = useState("");
    const [input2, setInput2] = useState("");

    // function to handle form submission
    const handleSubmit = async () => {

        // check if the input is empty
        if (input1.trim() === "") {
            Alert.alert("Error", "Please enter an item name.");
            return;
        }

        // get user id
        const {
            data: { user },
        } = await supabase.auth.getUser();

        if (!user) {
            Alert.alert("Error", "User not found.");
            return;
        }

        // insert new item into database
        const { error } = await supabase
            .from("lk_items")
            .insert({
                account_id: user.id,
                box_id: boxId,
                item_name: input1,
                item_descrip: input2,
            })
            .select();

        // Handle any errors during the insert operation
        if (error) {
            Alert.alert("Error", error.message);
        } else {
            Alert.alert("Success", "Item successfully added");
            navigation.navigate('boxes/[id]/itemIndex', { id: boxId})
        }
    };

    // function to handle account press
    const handleAccountPress = () => {
        navigation.navigate("account");
    };

    return (
        <View style={globalStyles.container}>
            {/* back button */}
            <TouchableOpacity
                style={globalStyles.backButton}
                onPress={() => navigation.navigate('boxes/[id]/itemIndex', { id: boxId })}
            >
                <MaterialIcons name="arrow-back" size={24} color="black" />
            </TouchableOpacity>

            {/* account button */}
            <TouchableOpacity
                style={globalStyles.accountButton}
                onPress={handleAccountPress}
            >
                <MaterialIcons name="account-circle" size={50} color="black" />
            </TouchableOpacity>

            {/* user input for item name */}
            <Input
                style={globalStyles.input}
                placeholder="Item Name"
                value={input1}
                onChangeText={setInput1}
            />

            {/* user input for item description */}
            <Input
                style={globalStyles.input}
                placeholder="Item Description"
                value={input2}
                onChangeText={setInput2}
            />

            <Button title="Submit" onPress={handleSubmit} />
        </View>
    );
}

const styles = StyleSheet.create({});
