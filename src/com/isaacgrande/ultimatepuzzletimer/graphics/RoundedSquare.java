package com.isaacgrande.ultimatepuzzletimer.graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.opengl.GLES20;

public class RoundedSquare {

    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            // The matrix must be included as a modifier of gl_Position.
            // Note that the uMVPMatrix factor *must be first* in order
            // for the matrix multiplication product to be correct.
            " gl_Position = uMVPMatrix * vPosition;" +
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            " gl_FragColor = vColor;" +
            "}";

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[];
    static short drawOrder[]; // order to draw vertices
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    float color[] = { 0.5f, 0f, 0f, 1.0f };

    /**
* Sets up the drawing object data for use in an OpenGL ES context.
*/
    public RoundedSquare(int numCornerTriangles, float cornerSize, float red, float green, float blue) {
    	squareCoords = new float[(12 + (numCornerTriangles)*4)*3];
    	drawOrder = new short[(10 + numCornerTriangles*4)*3];
    	
    	// add vertices for non rounded edges
    	// inner top left - 0
    	squareCoords[0] = -.5f + cornerSize;
    	squareCoords[1] = .5f - cornerSize;
    	squareCoords[2] = 0f;
    	// inner top right - 1
    	squareCoords[3] = .5f - cornerSize;
    	squareCoords[4] = .5f - cornerSize;
    	squareCoords[5] = 0f;
    	// inner bottom left - 2
    	squareCoords[6] = -.5f + cornerSize;
    	squareCoords[7] = -.5f + cornerSize;
    	squareCoords[8] = 0f;
    	// inner bottom right - 3
    	squareCoords[9] = .5f - cornerSize;
    	squareCoords[10] = -.5f + cornerSize;
    	squareCoords[11] = 0f;
    	// upper top left - 4
    	squareCoords[12] = -.5f + cornerSize;
    	squareCoords[13] = .5f;
    	squareCoords[14] = 0f;
    	// upper top right - 5
    	squareCoords[15] = .5f - cornerSize;
    	squareCoords[16] = .5f;
    	squareCoords[17] = 0f;
    	// leftmost top left - 6
    	squareCoords[18] = -.5f;
    	squareCoords[19] = .5f - cornerSize;
    	squareCoords[20] = 0f;
    	// leftmost bottom left - 7
    	squareCoords[21] = -.5f;
    	squareCoords[22] = -.5f + cornerSize;
    	squareCoords[23] = 0f;
    	// rightmost top right - 8
    	squareCoords[24] = .5f;
    	squareCoords[25] = .5f - cornerSize;
    	squareCoords[26] = 0f;
    	// rightmost bottom right - 9
    	squareCoords[27] = .5f;
    	squareCoords[28] = -.5f + cornerSize;
    	squareCoords[29] = 0f;
    	// lower bottom left - 10
    	squareCoords[30] = -.5f + cornerSize;
    	squareCoords[31] = -.5f;
    	squareCoords[32] = 0f;
    	// lower bottom right - 11
    	squareCoords[33] = .5f - cornerSize;
    	squareCoords[34] = -.5f;
    	squareCoords[35] = 0f;
    	
    	// add draw order for non rounded edges
    	// middle triangle 1
    	drawOrder[0] = 0;
    	drawOrder[1] = 1;
    	drawOrder[2] = 2;
    	// middle triangle 2
    	drawOrder[3] = 1;
    	drawOrder[4] = 3;
    	drawOrder[5] = 2;
    	// upper triangle 1
    	drawOrder[6] = 4;
    	drawOrder[7] = 5;
    	drawOrder[8] = 0;
    	// upper triangle 2
    	drawOrder[9] = 0;
    	drawOrder[10] = 5;
    	drawOrder[11] = 1;
    	// left triangle 1
    	drawOrder[12] = 7;
    	drawOrder[13] = 6;
    	drawOrder[14] = 0;
    	// left triangle 2
    	drawOrder[15] = 7;
    	drawOrder[16] = 0;
    	drawOrder[17] = 2;
    	// right triangle 1
    	drawOrder[18] = 3;
    	drawOrder[19] = 1;
    	drawOrder[20] = 8;
    	// right triangle 2
    	drawOrder[21] = 3;
    	drawOrder[22] = 8;
    	drawOrder[23] = 9;
    	// bottom triangle 1
    	drawOrder[24] = 10;
    	drawOrder[25] = 2;
    	drawOrder[26] = 3;
    	// bottom triangle 2
    	drawOrder[27] = 10;
    	drawOrder[28] = 3;
    	drawOrder[29] = 11;
    	
    	short prevTopLeft = 6;
    	short prevTopRight = 8;
    	short prevBottomLeft = 7;
    	short prevBottomRight = 9;
    	
    	// add vertices and draw order for all rounded edge triangles
    	for(int i = 1; i <= numCornerTriangles; ++i){
    		float xIncr = (float) ((double)cornerSize * Math.cos(0.5*Math.PI*((double)i/(double)numCornerTriangles)));
    		System.out.println("xIncr " + xIncr);
    		float yIncr = (float) ((double)cornerSize * Math.sin(0.5*Math.PI*((double)i/(double)numCornerTriangles)));
    		System.out.println("yIncr " + yIncr);
    		// top left - 12 + (i-1)*4
    		squareCoords[36 + (i-1)*12] = -.5f + cornerSize - xIncr;
        	squareCoords[36 + (i-1)*12 + 1] = .5f - cornerSize + yIncr;
        	squareCoords[36 + (i-1)*12 + 2] = 0f;
    		// top right - 12 + (i-1)*4 + 1
        	squareCoords[36 + (i-1)*12 + 3] = .5f - cornerSize + xIncr;
        	squareCoords[36 + (i-1)*12 + 4] = .5f - cornerSize + yIncr;
        	squareCoords[36 + (i-1)*12 + 5] = 0f;
    		// bottom left - 12 + (i-1)*4 + 2
        	squareCoords[36 + (i-1)*12 + 6] = -.5f + cornerSize - xIncr;
        	squareCoords[36 + (i-1)*12 + 7] = -.5f + cornerSize - yIncr;
        	squareCoords[36 + (i-1)*12 + 8] = 0f;
    		// bottom right - 12 + (i-1)*4 + 3
        	squareCoords[36 + (i-1)*12 + 9] = .5f - cornerSize + xIncr;
        	squareCoords[36 + (i-1)*12 + 10] = -.5f + cornerSize - yIncr;
        	squareCoords[36 + (i-1)*12 + 11] = 0f;
        	// top left triangle
        	drawOrder[30 + (i-1)*12] = 0;
        	drawOrder[30 + (i-1)*12 + 1] = prevTopLeft;
        	drawOrder[30 + (i-1)*12 + 2] = (short) (12 + (i-1) * 4);
        	// top right triangle
        	drawOrder[30 + (i-1)*12 + 3] = 1;
        	drawOrder[30 + (i-1)*12 + 4] = (short) (12 + (i-1)*4 + 1);
        	drawOrder[30 + (i-1)*12 + 5] = prevTopRight;
        	// bottom left triangle
        	drawOrder[30 + (i-1)*12 + 6] = 2;
        	drawOrder[30 + (i-1)*12 + 7] = prevBottomLeft;
        	drawOrder[30 + (i-1)*12 + 8] = (short) (12 + (i-1)*4 + 2);
        	// bottom right triangle
        	drawOrder[30 + (i-1)*12 + 9] = 3;
        	drawOrder[30 + (i-1)*12 + 10] = (short) (12 + (i-1)*4 + 3);
        	drawOrder[30 + (i-1)*12 + 11] = prevBottomRight;
        	
        	prevTopLeft = (short) (12 + (i-1)*4);
        	prevTopRight = (short) (12 + (i-1)*4 + 1);
        	prevBottomLeft = (short) (12 + (i-1)*4 + 2);
        	prevBottomRight = (short) (12 + (i-1)*4 + 3);
    	}
    	
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
        // (# of coordinate values * 4 bytes per float)
                squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        // prepare shaders and OpenGL program
        int vertexShader = CubeRenderer.loadShader(
                GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = CubeRenderer.loadShader(
                GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        mProgram = GLES20.glCreateProgram(); // create empty OpenGL Program
        GLES20.glAttachShader(mProgram, vertexShader); // add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(mProgram); // create OpenGL program executables
    }

    /**
* Encapsulates the OpenGL ES instructions for drawing this shape.
*
* @param mvpMatrix - The Model View Project matrix in which to draw
* this shape.
*/
    public void draw(float[] mvpMatrix) {
        // Add program to OpenGL environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        CubeRenderer.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        CubeRenderer.checkGlError("glUniformMatrix4fv");

        // Draw the square
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES, drawOrder.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
