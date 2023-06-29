//
//  ContentView.swift
//  recipeAppiOS
//
//  Created by Adam McNeilly on 6/28/23.
//

import shared
import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            Text(Greeting().greet())
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
