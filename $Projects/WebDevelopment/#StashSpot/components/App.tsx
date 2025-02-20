import React from "react";
import { Stack } from "expo-router";
import Auth from "@/components/Auth";
import { useSession } from "@/context/session.context";
import { createStackNavigator } from "@react-navigation/stack";

export default function App() {
    const session = useSession();
    const AuthenticatedStack = createStackNavigator();


    if (session && session.user) {
        return (
            <React.Fragment>
                <Stack>
                    <Stack.Screen
                        name="(tabs)"
                        options={{ headerShown: false }}
                    />
                    <Stack.Screen name="Account" />
                    <Stack.Screen name="+not-found" />
                    {/* <Stack.Screen name="Auth/index" options={{ title: "Login" }} />
                    <Stack.Screen name="Auth/signup" options={{ title: "Sign Up" }} /> */}
                </Stack>
            </React.Fragment>
        );
    }

    return <Auth />;
}