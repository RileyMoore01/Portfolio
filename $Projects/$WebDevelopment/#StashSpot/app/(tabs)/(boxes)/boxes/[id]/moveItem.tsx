import React, { useState, useEffect } from "react";
import { supabase } from "@/lib/supabase";
import { StyleSheet, View, TouchableOpacity } from "react-native";
import { useLocalSearchParams } from "expo-router";
import { MaterialIcons } from "@expo/vector-icons";
import { useNavigation } from '@react-navigation/native';
import { globalStyles } from "@/constants/globalStyles";
import { Database } from "@/database.types";

export default function MoveBox() {
    const { id } = useLocalSearchParams(); // param from the URL
    let itemId = Number(id);
    const navigation = useNavigation();

    // list of location 
    const [boxes, setBoxes] = useState<
        Database["public"]["Tables"]["lk_items"]["Row"][]
    >([]);
    const [selectedBox, setSelectedBox] = useState<string | null>(null);

    useEffect(() => {
        const fetchLocations = async () => {
            const { data: { user }, } = await supabase.auth.getUser();

            if (!user) {
                console.log("Error", "User not found");
                return;
            }

            // set the current location
            const { data: { box_id } } = await supabase
            .from("lk_items")
            .select("box_id")
            .eq("id", itemId)
            .eq('account_id', user.id)
            .single();
        
            setSelectedBox(box_id.toString());

            const { data: locationData, error: locationError } = await supabase
                .from("lk_boxes")
                .select("location_id")
                .eq("id", box_id)
                .eq('account_id', user.id)
                .single();

            if (locationError || !locationData) {
                console.error("Error fetching location_id:", locationError);
                return;
            }

            const { location_id } = locationData;
            console.log(location_id)

            const { data, error } = await supabase
                .from("lk_boxes")
                .select("id, name")
                .eq('account_id', user.id)
                .eq('location_id', location_id);

            if (error) {
                console.error("Error fetching locations:", error);
            } else {
                setBoxes(data);
            }
        };

        fetchLocations();
    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();

        // get current user
        const { data: { user }, } = await supabase.auth.getUser();
        if (!user) return;

        // get the box id to route back to the item index
        const { data: boxId } = await supabase
            .from("lk_items")
            .select("box_id")
            .eq("id", itemId)
            .eq("account_id", user.id)
            .single();

        const { error } = await supabase
            .from("lk_items")
            .update({ box_id: Number(selectedBox) })
            .eq("id", itemId)
            .eq('account_id', user.id);

        if (error) {
            console.error("Error updating box location:", error);
        } else {
            navigation.navigate('boxes/[id]/itemIndex', { id: boxId?.box_id, refresh: true})
        }
    };

    const handleAccountPress = () => {
        navigation.navigate("account");
    };

    const handleLocationChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedBox(event.target.value);
        console.log(event.target.value)
    };

    return (
        <View style={globalStyles.container}>
            {/* back button */}
            <TouchableOpacity
                style={globalStyles.backButton}
                onPress={() => navigation.goBack()}
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

            <select value={selectedBox || ''} onChange={handleLocationChange} style={styles.select}>
                {boxes.map(location => (
                    <option key={location.id} value={location.id}>
                        {location.name}
                    </option>
                ))}
            </select>

            <button type="submit" style={styles.submitButton} onClick={handleSubmit}>
                    Submit
            </button>
        </View>
    );
}

const styles = StyleSheet.create({
    select: {
        width: '100%',
        padding: 8,
        marginBottom: 10,
        borderRadius: 4,
    },
    submitButton: {
        width: '100%',
        padding: 10,
        backgroundColor: '#007BFF',
        borderRadius: 4,
        alignItems: 'center',
    },
});
