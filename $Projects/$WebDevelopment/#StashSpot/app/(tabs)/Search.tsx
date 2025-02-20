import React, { useState, useEffect } from 'react';
import { StyleSheet, FlatList, View, Alert } from 'react-native';
import { Text, Input, SearchBar } from '@rneui/themed';
import { supabase } from '@/lib/supabase';

export default function TabTwoScreen() {
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState<any[]>([]);

  const handleSearch = async (query: string) => {
    setSearchQuery(query);

    const { data: { user } } = await supabase.auth.getUser();

    if (!user) {
      Alert.alert('Error', 'User not found');
      return;
    }

    if (query.trim() === '' || !query) {
      const { data, error } = await supabase
        .from('lk_items')
        .select('*')
        .ilike('item_name', `%${query}%`)
        .eq('account_id', user.id);

      if (error) {
        Alert.alert('Error');
      }
  
      setSearchResults(data || []);
      return;
    }
    
    let boxId = -1;
    if (!isNaN(Number(query))) {
      boxId = Number(query);
    }

    const { data, error } = await supabase
      .from('lk_items')
      .select('*')
      .or(`item_name.ilike.%${query}%,box_id.eq.${boxId}`)
      .eq('account_id', user.id);

      if (error) {
        Alert.alert('Error getting data');
      }

      setSearchResults(data || []);
  };

  useEffect(() => {
    handleSearch('');
  }, []);

  return (
    <View style={styles.container}>
      <SearchBar
        placeholder="Search by item name or box ID..."
        value={searchQuery}
        onChangeText={handleSearch}
      />
      <FlatList
        data={searchResults}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <View style={styles.itemContainer}>
            <Text>{item.item_name}</Text>
            <Text>Box ID: {item.box_id}</Text>
          </View>
        )}
        style={{ backgroundColor: 'white' }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  itemContainer: {
    padding: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
  },
});
