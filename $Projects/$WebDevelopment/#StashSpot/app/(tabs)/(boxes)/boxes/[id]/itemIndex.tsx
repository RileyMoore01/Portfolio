import { Text } from "@rneui/themed";
import { supabase } from "@/lib/supabase";
import { Database } from "@/database.types";
import { useNavigation, useFocusEffect, Link } from "@react-navigation/native";
import { useLocalSearchParams } from "expo-router";
import { MaterialIcons } from "@expo/vector-icons";
import { View, Alert, TouchableOpacity, Modal, Pressable, FlatList } from "react-native";
import { globalStyles } from "@/constants/globalStyles";
import React, { useState, useCallback } from "react";

const ItemIndex = () => {
    const { id } = useLocalSearchParams();
    const boxId = Number(id);
    const navigation = useNavigation();
    const [items, setItems] = useState<
        Database["public"]["Tables"]["lk_items"]["Row"][]
    >([]);
    
    const [boxName, setBoxName] = useState("");
    const [modalVisible, setModalVisible] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);
    const [confirmDeleteVisible, setConfirmDeleteVisible] = useState(false);
    const [refresh, setRefresh] = useState(false);

    useFocusEffect(
        useCallback(() => {
            const fetchItemData = async () => {
                const {
                    data: { user },
                } = await supabase.auth.getUser();

                if (!user) {
                    Alert.alert("Error", "User not found");
                    return;
                }

                const { data: boxName } = await supabase
                    .from("lk_boxes")
                    .select("name")
                    .eq("id", boxId)
                    .eq("account_id", user.id)
                    .single();

                if (boxName) {
                    if (boxName.name !== null) {
                        setBoxName(boxName.name);
                    } else {
                        Alert.alert("Error", "Location name is null");
                    }
                } else {
                    Alert.alert("Error", "No location found");
                }

                const { data } = await supabase
                    .from("lk_items")
                    .select("*")
                    .eq("account_id", user.id)
                    .eq("box_id", boxId);

                if (data) {
                    setItems(data);
                }
            };

            fetchItemData();
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
                .from("lk_items")
                .delete()
                .eq("account_id", user.id)
                .eq("id", selectedItem.id);
    
            if (error) {
                Alert.alert("Error", "Failed to delete location");
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

    const handleBackButton = async () => {
        const { data: { user }, } = await supabase.auth.getUser();
        
        if (!user) {
            Alert.alert("Error", "User not found");
            return;
        }

        // get the location id
        const { data: { location_id } } = await supabase
        .from('lk_boxes')
        .select('location_id')
        .eq('id', boxId)
        .eq('account_id', user.id)
        .single();

        navigation.navigate('boxes/[id]/boxIndex', { id: location_id  });
    };

    const renderItem = ({ item }) => (
        <View>
            <TouchableOpacity 
                style={globalStyles.card}
                onPress={() => navigation.navigate('boxes/[id]/editItem', { id: item.id })}>
                    <Text>{item.item_name}</Text>
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
                    onPress={handleBackButton}
                >
                    <MaterialIcons name="arrow-back" size={24} color="black" />
                </TouchableOpacity>

                <Text h1>{boxName}</Text>
                <Link to={`/boxes/${id}/addItem`}>
                    <TouchableOpacity
                        style={[globalStyles.button, globalStyles.buttonAdd]}
                    >
                        <Text style={globalStyles.buttonText}>Add Items</Text>
                    </TouchableOpacity>
                </Link>
            </View>

            <FlatList
                data={items}
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
                                    navigation.navigate('boxes/[id]/editItem', { id: selectedItem.id })
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
                                    navigation.navigate('boxes/[id]/moveItem', { id: selectedItem.id })
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
                                Are you sure you want to delete this item?
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

export default ItemIndex;