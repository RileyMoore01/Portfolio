import React, { useState, useEffect } from 'react';
import { View, StyleSheet, Alert, TouchableOpacity } from 'react-native';
import { Input, Button } from '@rneui/themed';
import { supabase } from '@/lib/supabase';
import { useNavigation, useRoute } from '@react-navigation/native';
import { MaterialIcons } from '@expo/vector-icons';
import { globalStyles } from '@/constants/globalStyles';
import { useLocalSearchParams } from 'expo-router';

export default function EditItem() {
    const navigation = useNavigation();
    const { id } = useLocalSearchParams();

    const [input1, setInput1] = useState('');

    
    useEffect(() => {
        const fetchItemData = async () => {
            const { data: { user }, } = await supabase.auth.getUser();
            const locationId = Number(id);

            if (!user) {
                Alert.alert("Error", "User not found");
                return;
            }
        

            const { data, error } = await supabase
                .from('lk_location')
                .select('name')
                .eq('id', locationId)
                .eq('account_id', user.id)
                .single();

            if (error) {
                Alert.alert('Error', error.message);
            } else if (data) {
                setInput1(data.name);
            }
        };

        fetchItemData();
    }, [id]);

    const handleSave = async () => {
        const { data: { user }, } = await supabase.auth.getUser();
        const locationId = Number(id);

        if (!user) {
            Alert.alert("Error", "User not found");
            return;
        }

        const { error } = await supabase
            .from('lk_location')
            .update({
                name: input1,
            })
            .eq('id', locationId)
            .eq('account_id', user.id);

        if (error) {
            Alert.alert('Error', error.message);
        } else {
            Alert.alert('Success', 'Item successfully updated');
            navigation.navigate('index');
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
                onPress={() => navigation.navigate('index')}
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