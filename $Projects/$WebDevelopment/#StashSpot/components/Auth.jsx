import React, { useState } from 'react';
import { Alert, StyleSheet, View, AppState, Text, TouchableOpacity } from 'react-native';
import { supabase } from '../lib/supabase';
import { Button, Input } from '@rneui/themed';
import { MaterialIcons } from "@expo/vector-icons";

// Tells Supabase Auth to continuously refresh the session automatically if
// the app is in the foreground. When this is added, you will continue to receive
// `onAuthStateChange` events with the `TOKEN_REFRESHED` or `SIGNED_OUT` event
// if the user's session is terminated. This should only be registered once.
AppState.addEventListener('change', state => {
  if (state === 'active') {
    supabase.auth.startAutoRefresh();
  } else {
    supabase.auth.stopAutoRefresh();
  }
});

export default function Auth() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [loading, setLoading] = useState(false);
  const [showSignUpFields, setShowSignUpFields] = useState(false);
  
  // messages for the user
  const [message, setMessage] = useState('');
  const [message2, setMessage2] = useState('');

  async function signInWithEmail() {
    setLoading(true);
    const { error } = await supabase.auth.signInWithPassword({
      email: email,
      password: password,
    });

    if (error) setMessage('Email or password is incorrect!');
    setLoading(false);
  }

  async function signUpWithEmail() {
    setLoading(true);

    // check that passwords match
    if (password !== confirmPassword) {
      setMessage('Passwords do not match.');
      return;
    }
    
    const {
      data: { session },
      error,
    } = await supabase.auth.signUp({
      email: email,
      password: password,
    });

    if (error) {
        Alert.alert(error.message);
    } else {
      setMessage('Please check your inbox for email verification!');
      setMessage2('Please re-enter your credentials once you have verified your email.');
    }

    if (!session)  {
      Alert.alert('Please check your inbox for email verification!');
      console.log('test')
    }
    setLoading(false);
  }

  return (
    <View style={styles.container}>
      {showSignUpFields && (
        <TouchableOpacity
          style={styles.backButton}
          onPress={() => setShowSignUpFields(false)}
        >
          <MaterialIcons name="arrow-back" size={24} color="black" />
        </TouchableOpacity>
      )}
      <View style={[styles.verticallySpaced, styles.mt20]}>
        <Text style={styles.header}>Stash Spot</Text>

        <Input 
            label="Email" 
            // leftIcon={{ type: 'font-awesome', name: 'envelope' }} 
            onChangeText={text => setEmail(text)} 
            value={email} placeholder="email@address.com" 
            autoCapitalize={'none'}
            inputContainerStyle={styles.inputContainer}
        />
      </View>

      <View style={styles.verticallySpaced}>
        <Input
          label="Password"
          // leftIcon={{ type: 'font-awesome', name: 'lock' }}
          onChangeText={text => setPassword(text)}
          value={password}
          secureTextEntry={true}
          placeholder="Password"
          autoCapitalize={'none'}
          inputContainerStyle={styles.inputContainer}
        />
      </View>

      {showSignUpFields && (
        <>
          <View>
            <Input
                label="Confirm Password"
                style={styles.verticallySpaced}
                placeholder="Confirm Password"
                secureTextEntry={true}
                value={confirmPassword}
                autoCapitalize={'none'}
                inputContainerStyle={styles.inputContainer}
                onChangeText={(text) => setConfirmPassword(text)}
            />
          </View>

          <View>
            <Input
                label="First Name"
                style={styles.verticallySpaced}
                placeholder="First Name"
                value={firstName}
                inputContainerStyle={styles.inputContainer}
                onChangeText={(text) => setFirstName(text)}
            />
          </View>

          <View>
            <Input
                label="Last Name"
                style={styles.verticallySpaced}
                placeholder="Last Name"
                value={lastName}
                inputContainerStyle={styles.inputContainer}
                onChangeText={(text) => setLastName(text)}
            />
          </View>
        </>
      )}


      
      {!showSignUpFields && (
        <View style={[styles.verticallySpaced, styles.mt20]}>
          <Button title="Sign in" disabled={loading} onPress={() => signInWithEmail()} />
        </View>
      )}

      <View style={styles.verticallySpaced}>
      <Button
          title={showSignUpFields ? "Submit" : "Sign Up"}
          onPress={() => {
            if (showSignUpFields) {
              signUpWithEmail();
            } else {
              setShowSignUpFields(true);
            }
          }}
          disabled={loading}
        />
        {/* <Button title="Sign up" disabled={loading} onPress={() => signUpWithEmail()} /> */}
      </View>

      {message ? <Text style={styles.message}>{message}</Text> : null}
      {message2 ? <Text style={styles.message}>{message2}</Text> : null}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 12,
    backgroundColor: 'white',
  },
  headerContainer: {
    paddingVertical: 20,
    paddingHorizontal: 15,
    borderRadius: 10,
    marginVertical: 20,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.8,
    shadowRadius: 2,
    elevation: 5,
},
  header: {
    fontSize: 35,
    fontWeight: 'bold',
    textAlign: 'center',
    marginVertical: 20,
},
  verticallySpaced: {
    paddingTop: 4,
    paddingBottom: 4,
    alignSelf: 'stretch',
  },
  mt20: {
    marginTop: 20,
  },  
  inputContainer: {
    backgroundColor: 'white', 
    borderRadius: 5,
    paddingHorizontal: 10,
  },
  message: {
        color: 'red',
        padding: 10,
        marginTop: 20,
        textAlign: 'center',
    },
});
