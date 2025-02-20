import { Stack } from 'expo-router';

export default function BoxesLayout() {
  return (
    <Stack screenOptions={{ headerShown: false }}>
      <Stack.Screen name="index" />
      <Stack.Screen name="boxes/[id]" />
      <Stack.Screen name="boxes/[id]/addBox" />
      <Stack.Screen name="boxes/[id]/moveBox" />
      <Stack.Screen name="boxes/[id]/moveItem" />
      <Stack.Screen name="boxes/[id]/addItem" />
      <Stack.Screen name="boxes/[id]/editBox" />
      <Stack.Screen name="boxes/[id]/editItem" />
      <Stack.Screen name="boxes/[id]/editLocation" />
      <Stack.Screen name="boxes/[id]/itemIndex" />
      <Stack.Screen name="boxes/[id]/boxIndex" />
    </Stack>
  );
}
