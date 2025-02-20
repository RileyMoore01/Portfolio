import React, { useState } from "react";
import { supabase } from "@/lib/supabase";
import { Input, Button } from "@rneui/themed";
import { MaterialIcons } from "@expo/vector-icons";
import { globalStyles } from "@/constants/globalStyles";
import { useNavigation } from "@react-navigation/native";
import { View, StyleSheet, Alert, TouchableOpacity } from "react-native";

export default function AddLocation() {
    const navigation = useNavigation();
    const [input1, setInput1] = useState("");

    const handleSubmit = async () => {
        if (input1.trim() === "") {
            Alert.alert("Error", "Please enter a box name.");
            return;
        }

        const {
            data: { user },
        } = await supabase.auth.getUser();

        if (!user) {
            Alert.alert("Error", "User not found.");
            return;
        }

        const { error } = await supabase
            .from("lk_location")
            .insert({
                account_id: user.id,
                name: input1,
                location_id: 1,
            })
            .select();

        if (error) {
            Alert.alert("Error", error.message);
        } else {
            Alert.alert("Success", "Location successfully added");
            navigation.navigate("index");
        }
    };

    const handleAccountPress = () => {
        navigation.navigate("account");
    };

    return (
        <View style={globalStyles.container}>
            {/* back button */}
            <TouchableOpacity
                style={globalStyles.backButton}
                onPress={() => navigation.navigate("index")}
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

            {/* user input for location name */}
            <Input
                style={globalStyles.input}
                placeholder="Location Name"
                value={input1}
                onChangeText={setInput1}
            />
            <Button
                style={globalStyles.submitButton}
                title="Submit"
                onPress={handleSubmit}
            />
        </View>
    );
}

const styles = StyleSheet.create({});
