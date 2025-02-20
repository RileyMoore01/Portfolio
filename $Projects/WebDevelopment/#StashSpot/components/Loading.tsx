import { StyleSheet, View, ActivityIndicator } from 'react-native';

export default function Loading() {
  return (
    <View style={styles.container}>
      <ActivityIndicator size="large" color="#000000" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'white',
  },
});
