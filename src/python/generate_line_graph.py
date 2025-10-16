import math

import matplotlib.pyplot as plt
import sys
import os

from matplotlib.ticker import MultipleLocator


def create_line_chart(x_labels, y_values, x_title, y_title, title, image_name):
    try:
        # Create the Graphs directory if it doesn't exist
        if not os.path.exists('Graphs'):
            os.makedirs('Graphs')

        x = x_labels.split(',')
        # Convert strings of numbers to lists of integers
        y = list(map(float, y_values.split(',')))

        # Create a larger figure
        plt.figure(figsize=(14, 8))

        # Create the line chart
        plt.plot(x, y, marker='o')
        plt.title(title)
        plt.xlabel(x_title)
        plt.ylabel(y_title)

        # Rotate the x-axis labels for better readability
        plt.xticks(rotation=45, ha='right', fontsize=10)

        # Adjust y-axis limits
        y_min = min(y)
        y_max = max(y)

        if y_min == 0:
            min_padding = 0
        else:
            min_padding = 5

        y_range = y_max - y_min

        if y_max != y_min:
            max_padding = y_range * 0.1
        else:
            max_padding = 1

        plt.ylim(y_min - min_padding, y_max + max_padding)

        # Round to a nice power of ten
        step = 10 ** math.floor(math.log10(y_range / 10))

        # Get the current Axes object and set Y-axis ticks to appear every 'step' units
        plt.gca().yaxis.set_major_locator(MultipleLocator(step))

        #add a grid
        plt.grid(True, linestyle='--', alpha=0.6)
        # Adjust layout to avoid clipping the labels
        plt.tight_layout()

        # Save the line chart as an image in the Graphs folder
        image_path = os.path.join('Graphs', f'{image_name}.png')
        plt.savefig(image_path)
        
        # Close the plot to free up memory
        plt.close()

    except Exception as e:
        print(f"Error creating the graph: {e}")

if __name__ == '__main__':
    # Expecting x_values, y_values, x_title, y_title, title, and image_name as command-line arguments
    if len(sys.argv) == 7:
        x_labels = sys.argv[1]
        y_values = sys.argv[2]
        x_title = sys.argv[3]
        y_title = sys.argv[4]
        title = sys.argv[5]
        image_name = sys.argv[6]

        create_line_chart(x_labels, y_values, x_title, y_title, title, image_name)
    else:
        print("Error: Expected 6 arguments (x_labels, y_values, x_title, y_title, title, image_name)")
