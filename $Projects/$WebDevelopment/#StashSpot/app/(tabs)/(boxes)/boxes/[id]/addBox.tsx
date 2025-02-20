import React, { useState } from "react";
import { supabase } from "@/lib/supabase";
import { Input, Button } from "@rneui/themed";
import { useLocalSearchParams } from "expo-router";
import { MaterialIcons } from "@expo/vector-icons";
import { globalStyles } from "@/constants/globalStyles";
import { View, StyleSheet, Alert, TouchableOpacity } from "react-native";
import { useNavigation } from "@react-navigation/native";

export default function AddLocation() {
    const { id } = useLocalSearchParams(); // param from the URL
    const locationId = Number(id);

    const navigation = useNavigation(); // navigation object
    const [input1, setInput1] = useState(""); // user input for box name

     // Function to handle form submission
    const handleSubmit = async () => {

         // Check if the input is empty
        if (input1.trim() === "") {
            Alert.alert("Error", "Please enter a box name.");
            return;
        }
         // Get the current user
        const {
            data: { user },
        } = await supabase.auth.getUser();

        if (!user) {
            Alert.alert("Error", "User not found");
            return;
        }

        // Insert the new box into the database
        const { error } = await supabase
            .from("lk_boxes")
            .insert({
                account_id: user.id,
                name: input1,
                location_id: locationId,
            })
            .select();

        // Handle any errors during the insert operation
        if (error) {
            Alert.alert("Error", error.message);
        } else {
            Alert.alert("Success", "Box successfully added");
            navigation.navigate('boxes/[id]/boxIndex', { id: locationId });
        }
    };

    // Function to handle account button press
    const handleAccountPress = () => {
        navigation.navigate("account");
    };

    return (
        <View style={globalStyles.container}>
            {/* back button */}
            <TouchableOpacity
                style={globalStyles.backButton}
                onPress={() => navigation.navigate('boxes/[id]/boxIndex', { id: locationId })}
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

            {/* user input for box name */}
            <Input
                style={globalStyles.input}
                placeholder="Box Name"
                value={input1}
                onChangeText={setInput1}
            />

            <Button title="Submit" onPress={handleSubmit} />
        </View>
    );
}

const styles = StyleSheet.create({});
