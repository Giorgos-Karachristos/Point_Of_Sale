import matplotlib.pyplot as plt
import pandas as pd
import sys
from adjustText import adjust_text
import numpy as np
import os

def create_scatter_plot(names, x_values, y_values):
    try:
        # Create the Graphs directory if it doesn't exist
        if not os.path.exists('Graphs'):
            os.makedirs('Graphs')

        # Splitting the names string into a list
        n = names.split(',')

        # Convert strings of numbers to lists of integers/floats
        x = list(map(int, x_values.split(',')))
        y = list(map(float, y_values.split(',')))

        # Creating a dictionary to hold the data
        data = {
            'Item': n,
            'Sales Frequency': x,
            'Gross Margin (Profit)': y
        }

        # Creating a DataFrame from the data dictionary
        df = pd.DataFrame(data)

        # Create the plot
        fig, ax = plt.subplots(figsize=(8, 8))

        # Scatter plot
        ax.scatter(df['Sales Frequency'], df['Gross Margin (Profit)'])

        # Add numbers for each point
        texts = []
        for i, row in df.iterrows():
            text = ax.text(row['Sales Frequency'], row['Gross Margin (Profit)'], str(i+1), fontsize=12)
            texts.append(text)

        # Adjust the text labels to avoid overlaps
        adjust_text(texts, arrowprops=dict(arrowstyle='->', color='gray'))

        # Allow negatives
        ax.set_ylim(min(0, min(y) - 5), max(y) + 5)
        ax.set_xlim(min(0, min(x) - 5), max(x) + 5)

        # Median lines
        median_x = np.median(x)
        median_y = np.median(y)

        # Add horizontal and vertical lines to create quadrants
        plt.axhline(y=median_y, color='gray', linestyle='--', linewidth=1) # Horizontal line (Profit threshold)
        plt.axvline(x=median_x, color='gray', linestyle='--', linewidth=1) # Vertical line (Sales Frequency threshold)

        # Add quadrant labels
        ax.text(1, 1.05, 'Leaders', transform=ax.transAxes, fontsize=16, color='green', ha='right', va='top')
        ax.text(0, 1.05, 'Challengers', transform=ax.transAxes, fontsize=16, color='blue', ha='left', va='top')
        ax.text(0, -0.08, 'Niche Players', transform=ax.transAxes, fontsize=16, color='red', ha='left', va='bottom')
        ax.text(1, -0.08, 'Visionaries', transform=ax.transAxes, fontsize=16, color='purple', ha='right', va='bottom')

        plt.xlabel('Sales Frequency', fontsize=14)
        plt.ylabel('Gross Margin (Profit)', fontsize=14)
        plt.title('Magic Quadrant', fontsize=18, fontweight='bold')

        plt.grid(True, linestyle='--', alpha=0.5)
        plt.tight_layout()

        # Save the Magic Quadrant as an image in the Graphs folder
        plt.savefig(os.path.join('Graphs', 'magicQuadrant.png'))

        # Close the plot to free up memory
        plt.close()

    except Exception as e:
        print(f"Error creating the graph: {e}")

if __name__ == '__main__':
    if len(sys.argv) == 4:
        names = sys.argv[1]
        x_values = sys.argv[2]
        y_values = sys.argv[3]
        create_scatter_plot(names, x_values, y_values)
    else:
        print("Error: Expected 3 arguments (names, x_values, y_values)")
