import React, { useState, useEffect } from "react";
import { supabase } from "@/lib/supabase";
import { StyleSheet, View, TouchableOpacity, Button } from "react-native";
import { useLocalSearchParams } from "expo-router";
import { MaterialIcons } from "@expo/vector-icons";
import { useNavigation } from '@react-navigation/native';
import { globalStyles } from "@/constants/globalStyles";
import { Database } from "@/database.types";

export default function MoveBox() {
    const { id } = useLocalSearchParams(); // param from the URL
    let boxId = Number(id);
    const navigation = useNavigation();

    // list of location 
    const [locations, setLocations] = useState<
        Database["public"]["Tables"]["lk_location"]["Row"][]
    >([]);
    const [selectedLocation, setSelectedLocation] = useState<string | null>(null);

    useEffect(() => {
        const fetchLocations = async () => {
            const { data: { user }, } = await supabase.auth.getUser();

            if (!user) {
                console.log("Error", "User not found");
                return;
            }

            const { data, error } = await supabase
                .from("lk_location")
                .select("id, name")
                .eq('account_id', user.id);

            if (error) {
                console.error("Error fetching locations:", error);
            } else {
                setLocations(data);

                // set the current location
                const { data: { location_id } } = await supabase
                    .from("lk_boxes")
                    .select("location_id")
                    .eq("id", boxId)
                    .eq('account_id', user.id)
                    .single();
                
                setSelectedLocation(location_id.toString());
            }
        };

        fetchLocations();
    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();

        // get current user
        const { data: { user }, } = await supabase.auth.getUser();
        if (!user) return;

        // get the location id to route back to the item index
        const { data: locationId } = await supabase
            .from("lk_boxes")
            .select("location_id")
            .eq("id", boxId)
            .eq("account_id", user.id)
            .single();

        const { error } = await supabase
            .from("lk_boxes")
            .update({ location_id: Number(selectedLocation) })
            .eq("id", boxId)
            .eq('account_id', user.id);

        if (error) {
            console.error("Error updating box location:", error);
        } else {
            navigation.navigate('boxes/[id]/boxIndex', { id: locationId?.location_id, refresh: true})
        }
    };

    const handleAccountPress = () => {
        navigation.navigate("account");
    };

    const handleLocationChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedLocation(event.target.value);
        console.log(event.target.value)
    };

    const GetLocationReturn = async () => {
            const { data: { user }, } = await supabase.auth.getUser();
    
            if (!user) return;
    
            // get the location id
            const { data: { location_id } } = await supabase
            .from('lk_boxes')
            .select('location_id')
            .eq('id', boxId)
            .eq('account_id', user.id)
            .single();
            
            navigation.navigate('boxes/[id]/boxIndex', { id: location_id, refresh: true  });
        }

    return (
        <View style={globalStyles.container}>
            {/* back button */}
            <TouchableOpacity
                style={globalStyles.backButton}
                onPress={GetLocationReturn}
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

            <select value={selectedLocation || ''} onChange={handleLocationChange} style={styles.select}>
                {locations.map(location => (
                    <option key={location.id} value={location.id}>
                        {location.name}
                    </option>
                ))}
            </select>

            <Button title="Submit" onPress={handleSubmit} />
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
});
