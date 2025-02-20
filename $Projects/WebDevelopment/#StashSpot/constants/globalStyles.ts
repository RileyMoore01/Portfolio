import { StyleSheet } from "react-native";

export const globalStyles = StyleSheet.create({
    //--------------------------------------------------------------------------------------
    // styling for main pages -------------------------------------------
    //--------------------------------------------------------------------------------------
    accountButton: {
        position: "absolute",
        top: 10,
        right: 10,
        padding: 10,
      },
      reactLogo: {
        width: 100,
        height: 100,
        alignSelf: 'center',
      },
      titleContainer: {
        padding: 16,
        alignItems: 'center',
      },
      list: {
        padding: 16,
      },
      itemRow: {
        flexDirection: 'row',
        alignItems: 'center',
        position: 'relative',
      },
      editButton: {
        position: 'absolute',
        right: 0,
        paddingTop: 20,
      },
      card: {
        flex: 1,
        padding: 16,
        marginVertical: 8,
        marginRight: 30, 
        backgroundColor: '#fff',
        borderRadius: 8,
        shadowColor: '#000',
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.1,
        shadowRadius: 8,
        elevation: 2,
      },
      centeredView: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        marginTop: 22,
      },
      modalView: {
        margin: 20,
        backgroundColor: "white",
        borderRadius: 8,
        padding: 35,
        alignItems: "center",
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 2
        },
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 5
      },
      buttonContainer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginTop: 16,
      },
      button: {
        borderRadius: 8,
        padding: 15,
        margin: 10,
        elevation: 2
      },
      buttonClose: {
        backgroundColor: "#000000",
      },
      buttonEdit: {
        backgroundColor: "#000000",
      },
      buttonDelete: {
        backgroundColor: "#e74c3c",
      },
      buttonAdd: {
        backgroundColor: "#000000",
        color: "white",
      },
      buttonConfirm: {
        backgroundColor: "#e74c3c",
      },
      buttonCancel: {
        backgroundColor: "#000000",
      },
      buttonText: {
        color: "white",
        fontWeight: "bold",
        textAlign: "center"
      },
      textStyle: {
        color: "white",
        fontWeight: "bold",
        textAlign: "center"
      },
      closeIcon: {
        position: 'absolute',
        top: 5,
        left: 5,
        padding: 10,
      },

      //--------------------------------------------------------------------------------------
      // styling for input pages -------------------------------------------
      //--------------------------------------------------------------------------------------
      container: {
        flex: 1,
        justifyContent: 'center',
        padding: 16,
        backgroundColor: '#ffffff',
      },
      backButton: {
        position: 'absolute',
        top: 10,
        left: 10,
        padding: 10,
      },
      input: {
        height: 40,
        borderColor: '#ccc',
        borderWidth: 1,
        marginBottom: 12,
        paddingHorizontal: 8,
      },
      submitButton: {
        padding: 10,
        borderRadius: 8,
        marginVertical: 16,
      }
});