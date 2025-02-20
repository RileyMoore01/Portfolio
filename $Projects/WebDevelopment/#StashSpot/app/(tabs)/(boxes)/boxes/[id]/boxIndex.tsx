import { Text } from "@rneui/themed";
import { supabase } from "@/lib/supabase";
import { Database } from "@/database.types";
import { useLocalSearchParams } from "expo-router";
import { MaterialIcons } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";
import React, { useState, useEffect, useCallback } from "react";
import { Link } from "@react-navigation/native";
import { globalStyles } from "@/constants/globalStyles";
import {
    StyleSheet,
    FlatList,
    TouchableOpacity,
    Button,
    View,
    Alert,
    Pressable,
    Modal,
} from "react-native";
import { useFocusEffect } from "@react-navigation/native";

const BoxIndex = () => {
    const { id } = useLocalSearchParams();
    const [boxes, setBoxes] = useState<
        Database["public"]["Tables"]["lk_boxes"]["Row"][]
    >([]);
    
    const [locationName, setLocationName] = useState("");
    const [confirmDeleteVisible, setConfirmDeleteVisible] = useState(false);
    const [refresh, setRefresh] = useState(false);
    const [modalVisible, setModalVisible] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);
    const navigation = useNavigation();

    useFocusEffect(
        useCallback(() => {
            const fetchLocationData = async () => {
                const {
                    data: { user },
                } = await supabase.auth.getUser();
                const locationId = Number(id);

                if (!user) {
                    Alert.alert("Error", "User not found");
                    return;
                }

                const { data: locationData } = await supabase
                    .from("lk_location")
                    .select("name")
                    .eq("id", locationId)
                    .eq("account_id", user.id)
                    .single();

                if (locationData) {
                    if (locationData.name !== null) {
                        setLocationName(locationData.name);
                    } else {
                        Alert.alert("Error", "Location name is null");
                    }
                } else {
                    Alert.alert("Error", "No location found");
                }

                const { data } = await supabase
                    .from("lk_boxes")
                    .select("*")
                    .eq("account_id", user.id)
                    .eq("location_id", locationId);

                if (data) {
                    setBoxes(data);
                }
            };

            fetchLocationData();
        }, [refresh, id])
    );

    const handleMenu = (item) => {
        setSelectedItem(item);
        setModalVisible(true);
    };

    const handleDeletePress = (item) => {
        setSelectedItem(item);
        setConfirmDeleteVisible(true);
    };

    const handleDeleteConfirm = async () => {
        // get user id
        const {
            data: { user },
        } = await supabase.auth.getUser();

        // check for values
        if (!user) {
            Alert.alert("Error", "User not found");
            return;
        }

        if (!selectedItem) {
            Alert.alert("Error", "Location not found");
            return;
        }

        // delete logic
        const { error } = await supabase
            .from("lk_boxes")
            .delete()
            .eq("account_id", user.id)
            .eq("id", selectedItem.id);

        if (error) {
            Alert.alert("Error", "Failed to delete box");
            return;
        }

        // remove modal
        setConfirmDeleteVisible(false);
        setModalVisible(false);

        // trigger refresh
        setRefresh((prev) => !prev);
    };

    const handleDeleteCancel = () => {
        setConfirmDeleteVisible(false);
    };

    const handleAccountPress = () => {
        navigation.navigate("account");
    };

    const renderItem = ({ item }) => (
        <View>
            <TouchableOpacity 
                style={globalStyles.card}
                onPress={() => navigation.navigate('boxes/[id]/itemIndex', { id: item.id })}>
                    <Text>{item.name}</Text>
            </TouchableOpacity>

            <TouchableOpacity
                style={globalStyles.editButton}
                onPress={() => handleMenu(item)}
            >
                <MaterialIcons name="more-vert" size={24} color="black" />
            </TouchableOpacity>
        </View>
    );

    return (
        <View>
            <View style={globalStyles.titleContainer}>
                <TouchableOpacity
                    style={globalStyles.accountButton}
                    onPress={handleAccountPress}
                >
                    <MaterialIcons
                        name="account-circle"
                        size={50}
                        color="black"
                    />
                </TouchableOpacity>

                <TouchableOpacity
                    style={globalStyles.backButton}
                    onPress={() => navigation.navigate('index')}
                >
                    <MaterialIcons name="arrow-back" size={24} color="black" />
                </TouchableOpacity>

                <Text h1>{locationName}</Text>
                <Link to={`/boxes/${id}/addBox`}>
                    <TouchableOpacity
                        style={[globalStyles.button, globalStyles.buttonAdd]}
                    >
                        <Text style={globalStyles.buttonText}>Add Box</Text>
                    </TouchableOpacity>
                </Link>
            </View>
            <FlatList
                data={boxes}
                renderItem={renderItem}
                keyExtractor={(item) => item.id}
                contentContainerStyle={globalStyles.list}
            />

            {selectedItem && (
                <Modal
                    animationType="slide"
                    transparent={true}
                    visible={modalVisible}
                    onRequestClose={() => {
                        setModalVisible(!modalVisible);
                    }}
                >
                    <View style={globalStyles.centeredView}>
                        <View style={globalStyles.modalView}>
                            <TouchableOpacity
                                style={globalStyles.closeIcon}
                                onPress={() => setModalVisible(!modalVisible)}
                            >
                                <MaterialIcons
                                    name="close"
                                    size={24}
                                    color="black"
                                />
                            </TouchableOpacity>

                            <Pressable
                                style={[
                                    globalStyles.button,
                                    globalStyles.buttonEdit,
                                ]}
                                onPress={() => {
                                    setModalVisible(!modalVisible);
                                    navigation.navigate('boxes/[id]/editBox', { id: selectedItem.id })
                                }}
                            >
                                <Text style={globalStyles.textStyle}>Edit</Text>
                            </Pressable>

                            <Pressable
                                style={[
                                    globalStyles.button,
                                    globalStyles.buttonEdit,
                                ]}
                                onPress={() => {
                                    setModalVisible(!modalVisible);
                                    navigation.navigate('boxes/[id]/moveBox', { id: selectedItem.id })
                                }}
                            >
                                <Text style={globalStyles.textStyle}>Move</Text>
                            </Pressable>

                            <Pressable
                                style={[
                                    globalStyles.button,
                                    globalStyles.buttonDelete,
                                ]}
                                onPress={() => handleDeletePress(selectedItem)}
                            >
                                <Text style={globalStyles.textStyle}>
                                    Delete
                                </Text>
                            </Pressable>
                        </View>
                    </View>
                </Modal>
            )}

            {confirmDeleteVisible && (
                <Modal
                    animationType="slide"
                    transparent={true}
                    visible={confirmDeleteVisible}
                    onRequestClose={() => {
                        setConfirmDeleteVisible(!confirmDeleteVisible);
                    }}
                >
                    <View style={globalStyles.centeredView}>
                        <View style={globalStyles.modalView}>
                            <Text>
                                Are you sure you want to delete this box?
                            </Text>

                            <View style={globalStyles.buttonContainer}>
                                <Pressable
                                    style={[
                                        globalStyles.button,
                                        globalStyles.buttonConfirm,
                                    ]}
                                    onPress={handleDeleteConfirm}
                                >
                                    <Text style={globalStyles.textStyle}>
                                        Yes
                                    </Text>
                                </Pressable>

                                <Pressable
                                    style={[
                                        globalStyles.button,
                                        globalStyles.buttonCancel,
                                    ]}
                                    onPress={handleDeleteCancel}
                                >
                                    <Text style={globalStyles.textStyle}>
                                        No
                                    </Text>
                                </Pressable>
                            </View>
                        </View>
                    </View>
                </Modal>
            )}
        </View>
    );
}

const styles = StyleSheet.create({});

export default BoxIndex;
