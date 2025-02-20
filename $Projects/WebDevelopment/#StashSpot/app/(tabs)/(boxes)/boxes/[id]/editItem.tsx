import React, { useState, useEffect } from 'react';
import { View, StyleSheet, Alert, TouchableOpacity } from 'react-native';
import { Input, Button } from '@rneui/themed';
import { supabase } from '@/lib/supabase';
import { useNavigation, useRoute } from '@react-navigation/native';
import { MaterialIcons } from '@expo/vector-icons';
import { globalStyles } from '@/constants/globalStyles';
import { useLocalSearchParams } from "expo-router";

export default function EditItem() {
    const navigation = useNavigation();
    const { id } = useLocalSearchParams();
    const itemId = Number(id);
    
    const [input1, setInput1] = useState('');
    const [input2, setInput2] = useState('');

    useEffect(() => {
        const fetchItemData = async () => {
            const { data: { user }, } = await supabase.auth.getUser();

            if (!user) {
                Alert.alert("Error", "User not found");
                return;
            }
        

            const { data, error } = await supabase
                .from('lk_items')
                .select('item_name, item_descrip')
                .eq('id', itemId)
                .eq('account_id', user.id)
                .single();

            if (error) {
                Alert.alert('Error', error.message);
            } else if (data) {
                setInput1(data.item_name);
                setInput2(data.item_descrip);
            }
        };

        fetchItemData();
    }, [id]);

    const handleSave = async () => {
        const { data: { user }, } = await supabase.auth.getUser();

        if (!user) {
            Alert.alert("Error", "User not found");
            return;
        }

        const { error } = await supabase
            .from('lk_items')
            .update({
                item_name: input1,
                item_descrip: input2,
            })
            .eq('id', itemId)
            .eq('account_id', user.id);

        if (error) {
            Alert.alert('Error', error.message);
        } else {
            Alert.alert('Success', 'Item successfully updated');
            
            // get the box id to route back to the item index
            const { data: boxId } = await supabase
                                .from("lk_items")
                                .select("box_id")
                                .eq("id", itemId)
                                .eq("account_id", user.id)
                                .single();
            
            navigation.navigate('boxes/[id]/itemIndex', { id: boxId?.box_id, refresh: true })
        }
    };

    const handleAccountPress = () => {
        navigation.navigate('Account');
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

            <Button title="Save" onPress={handleSave} />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#ffffff',
    },
});