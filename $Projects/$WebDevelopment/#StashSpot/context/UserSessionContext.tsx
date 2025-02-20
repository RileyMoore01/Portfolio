// Testing a way to get unique user data for the entiren session
import React, { createContext, useContext, useState, useEffect } from "react";
import { supabase } from "@/lib/supabase";
import { Session } from "@supabase/supabase-js";

interface UserSessionContextProps {
    session: Session | null;
    user: any;
    accountId: string | null;
}

const UserSessionContext = createContext<UserSessionContextProps | undefined>(
    undefined
);

export const UserSessionProvider = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const [session, setSession] = useState<Session | null>(null);
    const [user, setUser] = useState<any>(null);
    const [accountId, setAccountId] = useState<string | null>(null);

    useEffect(() => {
        const getSession = async () => {
            const {
                data: { session },
            } = await supabase.auth.getSession();
            setSession(session);
            if (session) {
                const { data: userData } = await supabase
                    .from("users")
                    .select("*")
                    .eq("id", session.user.id)
                    .single();
                setUser(userData);
                // setAccountId(userData?.account_id || null);
            }
        };

        getSession();

        const { data: authListener } = supabase.auth.onAuthStateChange(
            (_event, session) => {
                setSession(session);
                if (session) {
                    const getUserData = async () => {
                        // const { data: userData } = await supabase
                        //     .from("users")
                        //     .select("*")
                        //     .eq("id", session.user.id)
                        //     .single();
                        // setUser(userData);
                        // setAccountId(userData?.account_id || null);
                    };
                    getUserData();
                } else {
                    setUser(null);
                    setAccountId(null);
                }
            }
        );

        return () => {
            authListener?.unsubscribe();
        };
    }, []);

    return (
        <UserSessionContext.Provider value={{ session, user, accountId }}>
            {children}
        </UserSessionContext.Provider>
    );
};

export const useUserSession = () => {
    const context = useContext(UserSessionContext);
    if (context === undefined) {
        throw new Error(
            "useUserSession must be used within a UserSessionProvider"
        );
    }
    return context;
};
