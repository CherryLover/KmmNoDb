import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
//         Main_iosKt.MainViewController()
        let mainViewController = ApplicationKt.Main()
        return mainViewController
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
//                 .ignoresSafeArea(.all, edges: .bottom) // Compose has own keyboard handler
    }
}



