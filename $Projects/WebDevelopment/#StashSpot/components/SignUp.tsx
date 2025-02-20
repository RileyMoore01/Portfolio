import React, { useState } from "react";
import { View, Text, StyleSheet, Alert } from "react-native";
import { Input, Button } from "@rneui/themed";
import { supabase } from "../lib/supabase";

export default function SignUp({ navigation }) {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    async function handleSignUp() {
        setLoading(true);
        const { error } = await supabase.auth.signUp({
            email: email,
            password: password,
        });

        if (error) {
            Alert.alert(error.message);
        } else {
            Alert.alert("Please check your inbox for email verification!");
            navigation.navigate("Auth");
        }
        setLoading(false);
    }

    return (
        <View style={styles.container}>
            <Text style={styles.header}>Sign Up</Text>
            <Input
                label="First Name"
                onChangeText={(text) => setFirstName(text)}
                value={firstName}
                placeholder="First Name"
                autoCapitalize="words"
                inputContainerStyle={styles.inputContainer}
            />
            <Input
                label="Last Name"
                onChangeText={(text) => setLastName(text)}
                value={lastName}
                placeholder="Last Name"
                autoCapitalize="words"
                inputContainerStyle={styles.inputContainer}
            />
            <Input
                label="Phone Number"
                onChangeText={(text) => setPhoneNumber(text)}
                value={phoneNumber}
                placeholder="Phone Number"
                keyboardType="phone-pad"
                inputContainerStyle={styles.inputContainer}
            />
            <Input
                label="Email"
                onChangeText={(text) => setEmail(text)}
                value={email}
                placeholder="email@address.com"
                autoCapitalize="none"
                inputContainerStyle={styles.inputContainer}
            />
            <Input
                label="Password"
                onChangeText={(text) => setPassword(text)}
                value={password}
                secureTextEntry={true}
                placeholder="Password"
                autoCapitalize="none"
                inputContainerStyle={styles.inputContainer}
            />
            <Button title="Sign Up" disabled={loading} onPress={handleSignUp} />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "white",
        padding: 12,
    },
    header: {
        fontSize: 24,
        fontWeight: "bold",
        textAlign: "center",
        marginVertical: 20,
    },
    inputContainer: {
        backgroundColor: "white",
        borderRadius: 5,
        paddingHorizontal: 10,
    },
});
